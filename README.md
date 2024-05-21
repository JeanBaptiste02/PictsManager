# PictsManager Backend

<div style="text-align:center;">
  <img src="./documentation/app_logo.png" alt="Project Logo" width="50" />
</div>

The PictsManager backend is built using Java Spring Boot framework. It provides RESTful APIs to handle user authentication, album management, picture uploading, and other related functionalities. This backend is designed to work in conjunction with the PictsManager mobile application.

## Setup

To set up the backend locally, follow these steps:

1. Clone this repository to your local machine :
```
git@github.com:JeanBaptiste02/PictsManager.git
```
2. Ensure you have Java JDK and Maven installed on your system.
3. Install Docker and Docker Compose for containerization and orchestration.
4. Build the Docker container for the backend using the provided Dockerfile :
```
docker build -t pictsmanagerapp .
```
5. Run the Docker container using Docker.
```
docker run -p 8080:8080 pictsmanagerapp:latest
```

## Usage

Once the backend is set up and running, you can interact with it using the provided RESTful APIs. You can use tools like Postman or curl to make requests to these endpoints.

## API Endpoints

Detailed documentation of the available API endpoints can be found in the file `Documentation_Technique_T-DEV-PAR8.pdf`.

## Authentication

Authentication is handled using JWT (JSON Web Tokens). When a user logs in successfully, they receive a JWT token which they must include in the headers of subsequent requests to access protected endpoints.

## Security

Security measures are implemented at various levels including authentication, authorization, input validation, and data encryption. Passwords are hashed before storing them in the database to ensure security.

## Contributing

Contributions to the PictsManager backend are welcome!
