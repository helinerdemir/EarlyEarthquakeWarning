@Entity
@Table(name = "earthquakes")
public class Earthquake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double magnitude;
    private Double latitude;
    private Double longitude;
    private Double depth;
    
    @Column(name = "occurrence_time")
    private LocalDateTime occurrenceTime;
    
    private String location;
    private String status; // DETECTED, VERIFIED, CANCELLED
    
    // Getter ve Setter metodları
} 