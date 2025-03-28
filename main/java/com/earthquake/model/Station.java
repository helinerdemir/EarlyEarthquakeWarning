@Entity
@Table(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private Double latitude;
    private Double longitude;
    private Boolean isActive;
    
    // Getter ve Setter metodları
} 