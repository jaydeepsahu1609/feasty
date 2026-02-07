# Restaurant Management System (Feasty)

This project aims to design and implement a **Restaurant Management System (RMS)**, starting from a minimal usable system and **incrementally evolving it into a real-world, production-grade application**.

The focus is not just feature development, but understanding **real-world problems**, trade-offs, and design decisions encountered in backend-heavy systems.

This project serves as a hands-on exploration of:
- System Design
- Database Design
- API Design
- Concurrency & Synchronization
- Spring Boot & JPA
- Scalable and maintainable architecture

This project is built as a **learning-focused, production-inspired system**, not a CRUD demo.  
The emphasis is on reasoning, design decisions, and scalability rather than quick feature delivery.

---

## Goals

- Model real restaurant workflows (waiter, chef, cashier, manager)
- Avoid overengineering in early stages
- Evolve the system incrementally with clear design boundaries
- Emphasize correctness, clarity, and extensibility over quick hacks

---

## Functional Requirements

#### 1. Waiter View

The waiter is the primary interface between the system and the customer.

- Start a new order (Dine-in / Takeaway)
- Add or remove items from an order
- Update item quantity
- Add item-level comments  
  _(e.g. “less spicy”, “no onion”, “extra cheese”)_
- View order status
- Deliver the prepared order to the table

#### 2. Chef View

The chef focuses on preparation and operational feasibility.

- Receive notification when a new order is submitted
- View pending and in-progress orders
- Mark an item or order as `Prepared`
- Notify waiter when a dish is ready
- Reject an item with a reason if it cannot be prepared

#### 3. Manager View

The manager oversees operations, inventory, and analytics.

- Add / update items in inventory
- Categorize items (e.g. Starters, Main Course, Beverages)
- Monitor inventory levels
- Receive alerts when an item is running low
- Manage employee details (waiters, chefs, cashiers)
- Assign tables to customers
- View analytics dashboards *(advanced)*


#### 4. Cashier View

The cashier handles billing and payment status.

- View order bill
- Mark an order as `Paid` or `Unpaid`
- Close completed orders


## Non-Functional Requirements

*(Will be implemented progressively)*

- Scalability (support multiple concurrent orders)
- Consistency and data integrity
- Low coupling between UI and business logic
- API-first design
- Clear separation of concerns
- Graceful error handling and recovery
- Extensible data model for future features

---

## Features Roadmap

### Phase 1: Basic Version (Must-Have)

- Initiate an order
- Add / remove items from an order
- View open orders
- View detailed order (items, quantities, pending items)
- Manually mark an order as paid


### Phase 2: Advanced Features (Future Releases)

#### Analytics & Insights
- Popular / best-selling items
- Item pairing insights  
  _(e.g. Butter Chicken frequently ordered with Garlic Naan)_
- Average preparation time per item
- Order throughput per hour / day

#### Inventory Management
- Track item stock levels
- Automatic out-of-stock alerts
- Item ordering priority based on popularity

#### Customer Experience
- Order customization requests
- Smart item recommendations (item pairing, best selling etc.)
- Faster ordering based on historical preferences

#### Alerts & Monitoring
- Item running out of stock
- Order preparation delay
- SLA breach for preparation time

---

## Assumptions

- Single restaurant (multi-branch support can be added later)
- Orders are immutable once marked as `Paid`
- Inventory updates are eventually consistent

---

## Application Flow (Initial Draft)
The system handles customer interactions from table allocation to billing:

![Application Flow](/docs/first-draft-flow.png)

## System Design (Initial Draft)
The initial design highlights the main components and their interactions:

![System Design](/docs/first-draft-design.png)

---

## API Doc

This project uses `springdoc-openapi` for API-Documentation generation. Swagger doc will be available at [http://localhost:9090/swagger-ui/index.html](http://localhost:9090/swagger-ui/index.html)

## Database

This project uses H2 in-memory DB. H2 console can be accessed from [http://localhost:9090/h2-console](http://localhost:9090/h2-console)

---
