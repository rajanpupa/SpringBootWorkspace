### find distinct relationship
```
match(p: Person)-[r]->(m:Movie) return distinct type(r)

-- for Nodes use labels(n)
```