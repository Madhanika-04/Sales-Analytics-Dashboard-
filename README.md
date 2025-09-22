# Sales Analytics Dashboard 

SalesPulse is a console-based Java application that helps track, manage, and analyze sales data. Users can add sales records, view revenue summaries, find top-selling products, filter sales by region and also stores sales data in a database.

---
## Features
1. Add & manage sales records: Details like date, product name, quantity, price, and region.
2. View total revenue: Calculates total sales
3. Generate top-selling product report: Identify most sold products.
4. Filter sales by region: Display sales for a specific region.
5. Console-based interface: Simple text-based menu for easy interaction.
6. Modular class structure: Organized into classes like Sale, SalesManager, DBHandler, and Main.
7. Database (SQL) integration: Store and retrieve sales records from a database.
---
## Target Users
1. Students - Learning Java, OOP, and basic DBMS integration.
2. Small business owners - Who want simple sales tracking system.
---
## Class Structure
1. Sale: Stores individual sales data. (date, product, quantity, price, region)
2. DBHandler: Handles database connection, inserting sales, and querying totals and top products.
3. SalesManager: Calls DBHandler methods to manage sales.
4. Main: Console menu and user interaction.
---
## Technologies Used
-> Java 24.0.1  
-> SQL Database (MySQL)  
-> Java Collections (ArrayList)  
-> Console I/O (Scanner)  
-> LocalDate for date handling  
