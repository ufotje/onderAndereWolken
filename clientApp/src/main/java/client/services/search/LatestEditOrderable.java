package client.services.search;

import java.time.LocalDateTime;

public interface LatestEditOrderable {
    LocalDateTime getLatestEdit();

    int getLikes();
}
