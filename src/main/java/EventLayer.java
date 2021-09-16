import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventLayer {
    String startTime;
    String endTime;
    String date;
    String group;
    String subject;
    String classroom;
    boolean lection;
}
