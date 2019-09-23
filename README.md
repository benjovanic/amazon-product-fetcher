# Amazon Product Fetcher

## Application description

The purpose of this application is to fetch the dimensions, rank and category of an Amazon product using an ASIN. The products details are then shown on the front end and saved in the server DB.

There are two folders in the root:

- `./fe` includes the client React app.
- `./be` includes the server Spring Boot app.

## Running the front end

```
cd ./fe
npm start
```

This runs the client on `http://localhost:3000`

Run tests via:

```
npm t
```

## Running the back end

This project was built and tested on Java 13. The jar has been included in the repository to avoid having to build first.

```
cd ./be
java -jar amazon-product-fetcher-be-0.1.0.jar
```

This runs the server on `http://localhost:8080`

The easiest way to run and test this application is using IntelliJ.

Run tests via maven:

```
mvn test
```

## Front end structure

- Uses React 16.
- Simple Bootstrap application with form for entering ASIN.
- Products are listed below the form.
- Uses redux to store the products.

## Back end structure

- Uses Java 13, Maven and Spring Boot.
- Maintains the products in an in-memory H2 database. Data is lost when the server is restarted.
- API endpoints:
  - Get all products: `http://localhost:8080/products`
  - Fetch product from Amazon: `http://localhost:8080/products/fetch-product?asin=ASIN`
