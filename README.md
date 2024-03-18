# PictsManager Backend

The PictsManager backend is built using Java Spring Boot framework. It provides RESTful APIs to handle user authentication, album management, picture uploading, and other related functionalities. This backend is designed to work in conjunction with the PictsManager mobile application.

## Setup

To set up the backend locally, follow these steps:

1. Clone this repository to your local machine.
2. Ensure you have Java JDK and Maven installed on your system.
3. Install Docker and Docker Compose for containerization and orchestration.
4. Build the Docker container for the backend using the provided Dockerfile.
5. Run the Docker container using Docker Compose.

## Usage

Once the backend is set up and running, you can interact with it using the provided RESTful APIs. These APIs are documented in the [API Endpoints](#api-endpoints) section below. You can use tools like Postman or curl to make requests to these endpoints.

## API Endpoints

Detailed documentation of the available API endpoints can be found [here](#). Make sure to authenticate appropriately before accessing protected endpoints.

## Authentication

Authentication is handled using JWT (JSON Web Tokens). When a user logs in successfully, they receive a JWT token which they must include in the headers of subsequent requests to access protected endpoints.

## Security

Security measures are implemented at various levels including authentication, authorization, input validation, and data encryption. Passwords are hashed before storing them in the database to ensure security.

## Contributing

Contributions to the PictsManager backend are welcome! Please follow the guidelines outlined in [CONTRIBUTING.md](CONTRIBUTING.md) for making contributions.
