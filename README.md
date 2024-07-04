# parking-lot-service

### This is a parking lot service application built using Spring Boot and Java. It uses an in-memory database to manage parking spots for motorcycles, cars, and vans.
---

Assumptions
----------
1. The parking lot has 25 total spaces
2. The parking lot can hold motorcycles, cars and vans
3. The parking lot has motorcycle spots, compact car spots and regular spots
4. A car can park in a single compact spot, or a regular spot
5. A van can park, but it will take up 3 regular spots (for simplicity we don't need to make
sure these spots are beside each other)


Service endpoints
---
### Park vehicle
- URL: http://localhost:8080/parking/park
- Method: POST
- Request Body:
json
`{
  "numberPlate": "CAR111",
  "type": "CAR"
}`
- Response: `Vehicle parked -successfully-`

### Vehicle leaves parking lot
- URL: http://localhost:8080/parking/leave/CAR111
- Method: POST
- Response: `Vehicle left the parking lot`
  
### Find how many spots are remaining
- URL: http://localhost:8080/parking/remaining-spots
- Method: GET
- Response: `24`
  
### Check if all parking spots are taken for a given vehicle type
- URL: http://localhost:8080/parking/all-spots-taken/CAR
- Method: GET
- Response: `false`

  ---
  Technologies: Java, Spring Boot, H2 Database, JUnit.
