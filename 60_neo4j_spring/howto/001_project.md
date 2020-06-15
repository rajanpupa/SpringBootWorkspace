### How to comprehend the project

Neo4j has a build in movies database which you can install from neo4j browser using `:play movies`

### Basic entities

There are two node entities`MovieEntity` and `PersonEntity`. 
There is one relation entiy `RoleEntity` (ACTED_IN).

The (person) -[ACTED_IN {roles} ] -> (Movie)
The (person) -[DIRECTED ] -> (Movie)
The (person) -[PRODUCED ] -> (Movie)
The (person) -[WROTE ] -> (Movie)
The (person) -[REVIEWED ] -> (Movie)

So There are different endpoints created for these.


```
public class MovieEntity {
	@Id
    @GeneratedValue
    private Long id;
    private String title;
    private int released;
    private String tagline;
    @Relationship(type = "ACTED_IN", direction = Relationship.INCOMING)
    private List<RoleEntity> roles;
}
public class PersonEntity {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int born;
	@Relationship(type = "ACTED_IN") // default outgoing
	private List<MovieEntity> movies = new ArrayList<>();
	public PersonEntity(String name, int born) {
		this.name = name;
		this.born = born;
	}
}
@RelationshipEntity(type = "ACTED_IN")
public class RoleEntity {
   @Id
   @GeneratedValue
	private Long id;
   private List<String> roles = new ArrayList<>();
	@StartNode
	private PersonEntity person;
	@EndNode
	private MovieEntity movie;
	public RoleEntity(MovieEntity movie, PersonEntity actor) {
		this.movie = movie;
		this.person = actor;
	}
   public void addRoleName(String name) {
      if (this.roles == null) {
          this.roles = new ArrayList<>();
      }
      this.roles.add(name);
   }
}
```

