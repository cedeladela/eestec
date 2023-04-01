package si.cedeladela.mappers;

import si.cedeladela.dtos.LocationDto;
import si.cedeladela.entitete.Location;

import java.util.List;
import java.util.stream.Collectors;

public class LocationMapper {

    public static LocationDto locationToLocationDto(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(location.getId());
        locationDto.setName(location.getName());
        locationDto.setLatitude(location.getLatitude());
        locationDto.setLongitude(location.getLongitude());
        locationDto.setActive(location.getActive());
        return locationDto;
    }

    public static List<LocationDto> locationToLocationDto(List<Location> locations) {
        return locations.stream().map(LocationMapper::locationToLocationDto).collect(Collectors.toList());
    }

    public static Location locationDtoToLocation(LocationDto locationDto) {
        Location location = new Location();
        location.setId(locationDto.getId());
        location.setName(locationDto.getName());
        location.setLatitude(locationDto.getLatitude());
        location.setLongitude(locationDto.getLongitude());
        location.setActive(locationDto.getActive());
        return location;
    }
    public static List<Location> locationDtoToLocation(List<LocationDto> locations) {
        return locations.stream().map(LocationMapper::locationDtoToLocation).collect(Collectors.toList());
    }
}
