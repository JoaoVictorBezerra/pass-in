# Pass.in - API 👾 
___
Welcome to the _pass.in API_, the source of code repository dedicated to managing events and festivals.
###### Overview 🌐
The pass.in API is built with robust technologies, offering a reliable and efficient solution for handling various functionalities.
###### Stack 💻
- Java 17
- Springboot
- HSQL (In Memory Database)
- SonarQube (To do)
- PostgresSQL (To do)
##### Architecture 🏢
This project follow an [MVC Architecture](https://www.ramotion.com/blog/mvc-architecture-in-web-application/)
##### Documentation 📖
The documentation provides a comprehensive overview of pass.in API, facilitating understanding and integration for developers and users alike.
The documentation is crafted using Postman Collection, a powerful tool that gives access to consume RESTful web services.
##### Git Flow 🕹️

We follow the Git flow model modified for our type of use, an example can be seen below: [GIT Flow - Schema](https://devjoaovictor.s3.amazonaws.com/GIT+FLOW.png)

The flow works as follows:
- Our main branch is **master**, it reflects everything that is in **Production**
- A reflection of the master for our development environment is the **develop** branch, it needs to be as faithful as possible so we can test and not have problems in the production environment
- Every new feature developed, we **checkout the develop branch** and call the new branch with the name **feature**. For example **"feature/passin-xxx"**, at the end of development, we **merge the feature into develop**
- We have an approval environment and it is based on the **homolog** branch, this branch is where developers will be able to **test everything** that was developed before going into production, in the end we **merge with the master branch**
- We also have the **release** branch, it is basically an auxiliary branch that leaves the homologous branch, in it we separate important changes that **cannot be merged into the master at any time**, for example: features that depend on some mobile release, in the end, when the app is available for release, let us merge in **homolog** again
- Lastly we have the **hotfix** branch, it is basically used to **quickly fix errors in production**, it leaves the master branch, we correct it and **merge it in master and develop** to keep the code updated

**OBS: Every change to the approval and production environment must be reviewed by a code review**