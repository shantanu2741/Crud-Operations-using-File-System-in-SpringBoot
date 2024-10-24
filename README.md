# Crud-Operations-using-File-System-in-SpringBoot

This project provides a simple REST API to upload, download, update, and delete files on a server.

## Features
- Upload files
- Download files
- Update file content
- Delete files

## Technologies Used
- Spring Boot
- Java
- Maven
- Postman for testing

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone <your-repo-url>
   cd file-upload-rest-api
Open in an IDE Open the project in your IDE Eclipse.

Build the Project Use Maven to build the project.

bash
Copy code
mvn clean install
Run the Application Run the application. The server will start on http://localhost:8080.

## API Endpoints

Method	Endpoint	Description
- POST	/files/upload	Upload a file
- GET	/files/download/{filename}	Download a file
- PUT	/files/update/{filename}	Update file content
- DELETE	/files/delete/{filename}	Delete a file

## Testing with Postman

- 1. Upload File
Method: POST
URL: http://localhost:8080/files/upload
Body: Select form-data, key as file, and choose a file to upload.

- 2. Download File
Method: GET
URL: http://localhost:8080/files/download/{filename} (replace {filename} with the actual file name).

- 3. Update File
Method: PUT
URL: http://localhost:8080/files/update/{filename}
Body: Select x-www-form-urlencoded, key as content, and the value as the new content.

- 4. Delete File
Method: DELETE
URL: http://localhost:8080/files/delete/{filename} (replace {filename} with the file name you want to delete).
