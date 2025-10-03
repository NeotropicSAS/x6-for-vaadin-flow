# vaadin-flow-x6

This repository contains a Vaadin Flow add-on for the web component for the popular Javascript diagramming library X6.

- [Antv X6 – Official site](https://x6.antv.antgroup.com/)

The project that containing the x6 web component can be found in the following repository: [X6 Web Component](https://github.com/NeotropicSAS/webcomponent-x6)

In the web component project, you can perform tests or add functionalities.

> **Note:**
> 
> If you make modifications to the TypeScript file (x-6.ts) of the web component, please remember to replace it in the location where this file is found in the x6 add-on.
>

The TypeScript file (`x-6.ts`) of the web component can be found at the following location in the X6 add-on:

- x6-for-vaadin-flow-root/x6-for-vaadin-flow/src/main/resources/META-INF/resources/frontend/src/`x-6.ts`

## License

Apache License 2.0

## Requirements
* Maven 3.9+.
* Java 21.

> **Note:**
> 
>You can use your preferred IDE to execute the project by performing a "Clean and Install (Build in some IDEs)," and then running the program. However, if you prefer to execute it through the terminal, continue with the tutorial.
>
  
## Running the component demo

1. Navigate to the x6-flow project.
   
    ```bash
        cd x6-for-vaadin-flow-root/x6-for-vaadin-flow
    ```

2. Install in your local repository x6 Add-on project:
   
    > **Note:**
    >
    >If Java 21 is not set as your primary Java version, configure it as an environment variable:
    >
    > ```bash
    >   export JAVA_HOME=/ROUTE-TO-YOUR-JAVA21/
    >   PATH=$JAVA_HOME/bin:$PATH
    >   java --version # Check your Java version
    >```
    > And configure Maven
    >
    > ```bash
    >  export M2_HOME=/ROUTE_TO_YOUR_MAVEN-3.9+/
    >  export PATH=$JAVA_HOME/bin:$M2_HOME/bin:$PATH
    > ```

    ```bash
        mvn clean install --no-transfer-progress
    ```

3. Navigate to the x6-for-vaadin-flow-demo project.
   
    ```bash
        cd x6-for-vaadin-flow-root/x6-for-vaadin-flow-demo
    ```

4. Install in your local repository X6 demo project:
   
    ```bash
        mvn clean install --no-transfer-progress
    ```

5. Run your application.

    ```bash
        mvn exec:java -Dexec.mainClass=com.neotropic.flow.component.antvx6.demo.Application
    ```
   
    you'll be able to find the demo tutorial at: http://localhost:8080/

    |![Tutorial](tutorial.png) |
    |:--:|
    | ***X6 Component Demo Tutorial for Vaadin Flow*** |

    > **Note:**
    >
    > If you need to use the add-on in another Spring Boot application, simply add the dependency to that application's pom.xml:
    >
    > ```bash
    >    <dependency>
    >       <groupId>com.neotropic.flow.component</groupId>
    >       <artifactId>x6-for-vaadin-flow</artifactId>
    >       <version>0.1.0</version>
    >       <type>jar</type>
    >    </dependency>
    >```
    >

    ## Disclaimer

    Antv X6 and Vaadin trademarks are property of their respective owners. Neotropic SAS is not affiliated in any way to these companies. Use this component at your own risk.
