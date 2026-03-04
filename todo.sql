Create database springtodo;

CREATE TABLE IF NOT EXISTS categories(
CategoryID INT AUTO_INCREMENT PRIMARY KEY,
category_name VARCHAR(100) NOT NULL
);


CREATE TABLE IF NOT EXISTS tasks( 
TaskID INT AUTO_INCREMENT PRIMARY KEY,
task_name VARCHAR(100) NOT NULL, 
category_id INT, 
deadline VARCHAR(100) NOT NULL,
FOREIGN KEY (category_id) REFERENCES categories(CategoryID)
); 

-- must fill in both tables --
INSERT INTO categories(category_name) 
VALUES ("JavaScript"), ("Java"), ("Github"), ("Documentation");

INSERT INTO tasks(task_name, category_id, deadline) 
VALUES ("Finish the ES6 online course", 2, "08 Mar 2026"),
("Write Java testing code",  2, "18 Mar 2026") , 
("JavaScript code review", 1, "30 Mar 2026") , 
("Write a documentation", 4, "25 Apr 2026"), 
("Push updates to Github", 3, "20 Mar 2026");