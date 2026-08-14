package com.krikaliov.earthdistances;

import java.io.IOException;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class App {
  private final int width = 600;
  private final int height = 800;

  public final String title = Main.APPNAME + " " + Main.VERSION;

  protected final String unions() {
    String r = "";
    final int n = title.length() + 8;
    for (int i = 0 ; i < n ; i++) r = r.concat("-");
    return r;
  }

  private final String label = this.unions() + "\n--| " + this.title + " |--\n" + this.unions();

  private long window = 0;

  private final EarthDataView earthDataView;
  private final PinDataViewer pinDataViewer;

  private final Controller mainController;

  public App(int width, int height) {
    this.earthDataView = new EarthDataView(new EarthData());
    this.pinDataViewer = new PinDataViewer();

    this.mainController = new Controller(this);

    // Setup an error callback. The default implementation
    // will print the error message in System.err.
    GLFWErrorCallback.createPrint(System.err).set();

    // Initialize GLFW. Most GLFW functions will not work before doing this.
    if (!glfwInit()) {
      throw new IllegalStateException("Unable to initialize GLFW");
    }

    // Configure GLFW
    glfwDefaultWindowHints(); // optional, the current window hints are already the default
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

    // Create the window
    this.window = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
    if (this.window == NULL)
      throw new RuntimeException("Failed to create the GLFW window");

    // Setup a key callback. It will be called every time a key is pressed, repeated or released.
    glfwSetKeyCallback(this.window, (window, key, scancode, action, mods) -> {
      if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
        glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
    });

    // Get the thread stack and push a new frame
    try (MemoryStack stack = stackPush()) {
      final IntBuffer pWidth = stack.mallocInt(1); // int*
      final IntBuffer pHeight = stack.mallocInt(1); // int*

      // Get the window size passed to glfwCreateWindow
      glfwGetWindowSize(this.window, pWidth, pHeight);

      // Get the resolution of the primary monitor
      final GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

      // Center the window
      glfwSetWindowPos(this.window,
        (vidmode.width() - pWidth.get(0)) / 2,
        (vidmode.height() - pHeight.get(0)) / 2
      );
    } // the stack frame is popped automatically

    // Make the OpenGL context current
    glfwMakeContextCurrent(this.window);
    // Enable v-sync
    glfwSwapInterval(1);

    // Make the window visible
    glfwShowWindow(this.window);
  }

  public void loop() throws IOException {
    // This line is critical for LWJGL's interoperation with GLFW's
    // OpenGL context, or any context that is managed externally.
    // LWJGL detects the context that is current in the current thread,
    // creates the GLCapabilities instance and makes the OpenGL
    // bindings available for use.
    GL.createCapabilities();

    // Set the clear color
    glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

    // Run the rendering loop until the user has attempted to close
    // the window or has pressed the ESCAPE key.

    System.out.println(this.label);
    System.out.println();

    System.out.println(this.earthDataView);
    System.out.println();

    while (!glfwWindowShouldClose(this.window)) {
      System.out.println(this.pinDataViewer);

      this.mainController.waitForCmdLine();

      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

      glfwSwapBuffers(this.window); // swap the color buffers

      // Poll for window events. The key callback above will only be
      // invoked during this call.
      glfwPollEvents();
    }
  }

  public int[] getScreenSize() {
    final int[] size = new int[2];
    size[0] = this.width;
    size[1] = this.height;
    return size;
  }

  public final long windowHandler() {
    return this.window;
  }

  public boolean isAlive() {
    return !glfwWindowShouldClose(this.window);
  }

  public PinDataViewer pinViewer() {
    return this.pinDataViewer;
  }

  @SuppressWarnings("rawtypes")
  public final CommandFunction quitCmdFn = (Argument[] x) -> {
    System.out.println("Thank you for using my software! © krikaliov");
    glfwSetWindowShouldClose(this.window, true); // We will detect this in the rendering loop
  };
}
