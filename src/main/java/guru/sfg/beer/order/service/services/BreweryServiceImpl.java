package guru.sfg.beer.order.service.services;

import guru.sfg.beer.order.service.services.model.BeerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
public class BreweryServiceImpl implements BreweryService {
    private static final String BEER_PATH_V2 = "/v2/beer/";
    private static final String BEER_UPC_PATH_V2 = "/v2/beerUpc/";
    private final RestTemplate restTemplate;
    private final String breweryHostName;

    public BreweryServiceImpl(RestTemplateBuilder restTemplateBuilder,
                              @Value("${brewery-service-hostname}") String breweryHostName) {
        this.restTemplate = restTemplateBuilder.build();
        this.breweryHostName = breweryHostName;
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID id) {
        return Optional.of(restTemplate.getForObject(breweryHostName + BEER_PATH_V2 + id.toString(), BeerDto.class));
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(Long upc) {
        return Optional.of(restTemplate.getForObject(breweryHostName + BEER_UPC_PATH_V2 + upc, BeerDto.class));
    }
}
