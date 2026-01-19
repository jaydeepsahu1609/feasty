# Feasty – Spring Boot Restaurant Ordering & Billing System

A mini-project that simulates a simple Restaurant Management System, designed to demonstrate the following concepts:

- **Multi-threading, Concurrency, and Synchronization**
- **JPA (Java Persistence API) for ORM**
- **Database Design & Modeling**
- **Spring Boot Application Development**

## Application Flow
The system handles customer interactions from table allocation to billing:

![Application Flow](/docs/first-draft-flow.png)

## System Design (First Draft)
The initial design highlights the main components and their interactions:

![System Design](/docs/first-draft-design.png)

## Plans

#### Phase-1 : Core CRUD & Flow

**Entities**: Customer, Order, OrderItem, Item, RestaurantTable.

**Features**:

* Create customers, items, tables.
* Place orders, add items to orders.
* Track order item status (pending → preparing → ready → served).
* Basic listing endpoints: orders by customer, table, or status.

#### Phase-2 : Billing & Payment

* Add a Bill entity or just store total in Order.
* Handle CLOSED orders and bill calculation.
* Apply discounts, taxes, or offers (optional for simplicity).

#### Phase-3 : Kitchen / Chef workflow

* Add a Chef or Staff entity if needed.
* Assign OrderItem to a chef.
* Track preparation time, update statuses.

#### Phase-4 : Reporting / Analytics

* Number of orders per day / table / customer.
* Most popular items, out-of-stock alerts.

#### Phase-5 :Optional Enhancements

* Notifications to staff when items are ready.
* Notifications to Manager when inventory is about to exhaust.
* TO BE ADDED
