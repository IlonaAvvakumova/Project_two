SELECT* from Employee2;
SELECT* from manager;

SELECT e.Name as Employee
FROM
    Employee2 e INNER JOIN manager m
    ON e.ManagerID = m.ID
    where e.Salary > m.Salary;