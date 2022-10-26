package whoscared.cloudrest.services;

import org.springframework.stereotype.Service;
import whoscared.cloudrest.repositories.DataRepository;

@Service
public class DataServices {
    private final DataRepository dataRepository;

    public DataServices(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

}
