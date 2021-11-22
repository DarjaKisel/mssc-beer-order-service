package guru.sfg.beer.order.service.web.mappers;

import guru.sfg.beer.order.service.domain.BeerOrderLine;
import guru.sfg.beer.order.service.services.BreweryService;
import guru.sfg.beer.order.service.services.model.BeerDto;
import guru.sfg.beer.order.service.web.model.BeerOrderLineDto;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

@NoArgsConstructor
public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {

    private BreweryService breweryService;
    private BeerOrderLineMapper mapper;

    @Autowired
    public void setBreweryService(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @Autowired
    @Qualifier("delegate")
    public void setMapper(BeerOrderLineMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        BeerOrderLineDto orderLine = mapper.beerOrderLineToDto(line);
        Optional<BeerDto> beerDtoOptional = breweryService.getBeerByUpc(orderLine.getUpc());

        beerDtoOptional.ifPresent(beerDto -> {
            orderLine.setBeerName(beerDto.getBeerName());
            orderLine.setBeerStyle(beerDto.getBeerStyle());
            orderLine.setPrice(beerDto.getPrice());
            orderLine.setBeerId(beerDto.getId());
        });

        return orderLine;
    }
}
