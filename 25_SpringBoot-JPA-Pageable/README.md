# SpringBoot JPA Pageable

Spring JPA provides a capacity to fetch the data from database in terms of pages of certain size, sorted by certain fields.

This project uses `h2` database.

Include `spring-boot-starter-data-jpa` dependency.

* Create Pageable instance
```
  @GetMapping
	public Page<TestModel> test(
			@RequestParam(value="page", required=false, defaultValue="1")Integer page,
			@RequestParam(value="size", required=false, defaultValue="5")Integer size
			){
		Pageable pg = new PageRequest(page, size);
		Page<TestModel> pm = tr.findAll(pg);
		return pm;
	}
```

Extend the `QueryByExampleExecutor<>` interface from the `@Repository`

```
@Repository
public interface TestRepository extends JpaRepository<TestModel, Integer>, QueryByExampleExecutor<TestModel>{

}
```

### Sample request
`http://localhost:8080?page=6&size=2`

### Sample response
```
{
    "content": [
        {
            "id": 12,
            "text": "Hello 12"
        },
        {
            "id": 13,
            "text": "Hello 13"
        }
    ],
    "totalPages": 8,
    "totalElements": 15,
    "last": false,
    "size": 2,
    "number": 6,
    "sort": null,
    "first": false,
    "numberOfElements": 2
}
```
