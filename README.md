Servlet
==========
- Servlet is a server-side technology/component which is used to build dynamic webpages.
- It is used to handle client request, process the request and generate dynamic response.

Deployment Descriptor Tool(DDT)
  =========================
 - It is used to mapping the request or url with particular servlet.
  - In web application, It is named as web.xml


Types of Sevlet
================
1) javax.servlet.Servlet
2) javax.servlet.GenericServlet
3) 2) javax.servlet.HttpServlet

Mapping url with Servlet
=========================
There are two way to mappin url with servlet-
1)using ddt or web.xml(tag based mapping)
Example:
<!-- Servlet Mapping -->
    <servlet>
        <servlet-name>ExampleServlet</servlet-name>
        <servlet-class>com.example.ExampleServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>ExampleServlet</servlet-name>
        <url-pattern>/example</url-pattern> <!-- This maps the servlet to the URL "/example" -->
    </servlet-mapping>

2) Annotation based Mapping
   Example:
   ============
  @WebServlet("/example") // This annotation maps the servlet to the URL "/example"
  public class ExampleServlet extends HttpServlet {
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
          // Servlet logic here
          response.getWriter().println("This is an example servlet!");
      }
  }


GenericServlet
================
- A Generic Servlet is a class provided by the Java Servlet API that serves as a base implementation for servlets.
- It's a convenient way to create servlets without directly extending the HttpServlet class.

  Create GenericServlet:
  Extend javax.servlet.GenericServlet: To create a servlet, you create a subclass of GenericServlet and override the service(ServletRequest req,
  ServletResponse res) method to provide the logic for handling requests.

Example:
===============
public class MyGenericServlet extends GenericServlet {
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        res.getWriter().println("<html><body>");
        res.getWriter().println("<h1>Hello from Generic Servlet!</h1>");
        res.getWriter().println("</body></html>");
    }
}


HttpServlet
================
- HttpServlet is an abstract class provided by the Java Servlet API that extends the GenericServlet class and provides specific support for handling HTTP requests.
- It's the most commonly used base class for servlets in web applications.

- HttpServlet provides implementations for the HTTP methods like GET, POST, PUT, DELETE, etc., making it easier to handle different types of HTTP requests.

- Extend javax.servlet.http.HttpServlet: To create an HTTP servlet, you create a subclass of HttpServlet and override one or more of its doGet(), doPost(), doPut(), doDelete(), etc., methods to provide the logic for handling specific HTTP methods.

Example:
=============
public class MyHttpServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello from HttpServlet!</h1>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handling POST requests
    }
}

getParameter() method
=====================
The getParameter() method is provided by the ServletRequest class in the Java Servlet API. 
It's used to retrieve the value of a request parameter that was sent by the client as part of an HTTP request.
Example:
===========
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Retrieve the value of a request parameter named "name"
    String name = request.getParameter("name");

    // Use the parameter value in your servlet logic
    if (name != null && !name.isEmpty()) {
        response.getWriter().println("Hello, " + name + "!");
    } else {
        response.getWriter().println("Hello, guest!");
    }
}

RequestDispatcher
=================
The RequestDispatcher interface in the Java Servlet API allows a servlet to forward a request to another servlet or include the content of another resource (such as HTML, JSP, or another servlet) in the response.

There are two main methods provided by RequestDispatcher:

1) forward(ServletRequest request, ServletResponse response):
   - This method forwards the request from the current servlet to another servlet or resource on the server.
   - After the forward, the response is sent back to the client by the target servlet, and the current servlet does not send any response.
     Example:
     ========
     public class MyServlet extends HttpServlet {
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to another servlet
        RequestDispatcher dispatcher = request.getRequestDispatcher("/otherServlet");
        dispatcher.forward(request, response);
    }
}

2) include(ServletRequest request, ServletResponse response):
   - This method includes the content of another servlet or resource in the response of the current servlet.
   - The included servlet's response is sent back to the client along with the response of the current servlet.
     Example:
     ==========
     public class MyServlet extends HttpServlet {
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Include the content of another servlet
        RequestDispatcher dispatcher = request.getRequestDispatcher("/otherServlet");
        dispatcher.include(request, response);
    }
}


Difference between GenericServlet and HttpServlet
==================================================
1) GenericServlet Provides a generic, protocol-independent servlet whereas HttpServlet	Specifically designed to handle HTTP requests.
2) GenericServlet Suitable for protocols other than HTTP (e.g., FTP) whereas HttpServlet Used for building web applications that communicate over HTTP.
3) GenericServlet Provides service(ServletRequest, ServletResponse) for handling requests. Can override init() and destroy() methods whereas  Extends HttpServlet Provides implementations for HTTP methods like doGet(), doPost(), etc. Additional methods like doHead() are also available.
4) GenericServlet Supports various protocols beyond HTTP whereas HttpServlet Specifically tailored for HTTP protocol.
5) GenericServlet Rarely used directly for web development whereas HttpServlet Commonly used for building web applications.

Lifecycle Of Servlet
========================
The lifecycle of a servlet refers to the series of stages a servlet goes through from its initialization to its destruction. These stages are managed by the servlet container (e.g., Apache Tomcat) and are as follows:

Loading:

When a servlet container starts or receives the first request for a servlet, it loads the servlet class into memory.
The init() method of the servlet is called by the container to initialize the servlet. This method is called only once during the lifecycle of the servlet.
Initialization:

The init() method is used to perform any initialization tasks required by the servlet, such as establishing database connections, loading configuration parameters, etc.
This method is called by the servlet container after the servlet class is loaded into memory but before it starts servicing any requests.
Request Handling:

After initialization, the servlet is ready to handle client requests.
For each incoming request, the servlet container invokes the appropriate method (doGet(), doPost(), etc.) on the servlet instance to process the request.
The servlet processes the request, generates a response, and sends it back to the client.
Destruction:

When the servlet container decides to remove the servlet from service (e.g., when shutting down or when the servlet hasn't been used for a long time), it calls the servlet's destroy() method.
The destroy() method allows the servlet to perform any cleanup tasks, such as closing database connections or releasing any other resources it may have allocated during its lifecycle.
After the destroy() method is called, the servlet instance is eligible for garbage collection, and its memory is reclaimed by the JVM.

ServletConfig
================
ServletConfig is an interface provided by the Java Servlet API that allows servlets to retrieve initialization parameters and access information about their servlet context. 
It represents the configuration of a specific servlet instance within a servlet container.

ServletContext
=================
The ServletContext interface in the Java Servlet API represents a servlet's environment within the servlet container. 
It allows servlets to interact with the servlet container and access various resources and information about the web application it's deployed in.

Session Tracking
===================
Session tracking in web development refers to the process of maintaining stateful information about interactions between a web client (such as a browser) and a web application over a series of HTTP requests and responses. This is crucial for scenarios where you need to remember user-specific data across multiple requests, such as maintaining a user's login status, shopping cart contents, or preferences.

There are several techniques for session tracking in servlet-based web applications:

1) Cookies
===========
Cookies are small pieces of data stored on the client's browser and sent with each request to the server.
They can be used to store session identifiers or other session-related data. Servlets can use the javax.servlet.http.Cookie class to set and retrieve cookies.

2) URL Rewriting
===================
With URL rewriting, the web application includes the session identifier in the URLs it generates.
This session identifier is typically encoded as a query parameter or as part of the URL path.
 Servlets can use HttpServletResponse.encodeURL() to automatically append the session ID to URLs.

3) Hidden Form Fields
=========================
In this technique, the session identifier is included as a hidden field in HTML forms submitted by the client.
Servlets can retrieve the session identifier from these hidden form fields and use it to associate requests with sessions.

4) Session Tracking API
======================
Servlet containers typically provide a session tracking API that allows servlets to manage sessions explicitly.
Servlets can use methods like HttpServletRequest.getSession() to retrieve the current session or create a new session if one doesn't exist.
Sessions are typically backed by server-side data structures (e.g., HttpSession objects) and are associated with a unique session identifier.

Example:
=========
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve username and password from request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Perform authentication (this is a simplified example)
        boolean isAuthenticated = authenticate(username, password);

        if (isAuthenticated) {
            // Retrieve or create HttpSession
            HttpSession session = request.getSession(true);
            // Store user information in session
            session.setAttribute("username", username);

            // Redirect to a secure area
            response.sendRedirect("secure/dashboard.jsp");
        } else {
            // Redirect back to login page with error message
            response.sendRedirect("login.jsp?error=authentication_failed");
        }
    }

    private boolean authenticate(String username, String password) {
        // Perform authentication logic here (e.g., check credentials against database)
        // This is a simplified example, actual implementation will vary
        return "user123".equals(username) && "password123".equals(password);
    }
}


