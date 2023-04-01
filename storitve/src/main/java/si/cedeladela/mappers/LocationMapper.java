package si.cedeladela.mappers;

import si.cedeladela.dtos.LocationDto;
import si.cedeladela.dtos.StepathonUserDto;
import si.cedeladela.entitete.Location;
import si.cedeladela.entitete.StepathonUser;

import java.util.List;
import java.util.stream.Collectors;

public class LocationMapper {

    public static LocationDto locationToLocationDto(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setName(location.getName());
        locationDto.setLatitude(location.getLatitude());
        locationDto.setLongitude(location.getLongitude());
        return locationDto;
    }

    public static List<LocationDto> locationToLocationDto(List<Location> locations) {
        return locations.stream().map(LocationMapper::locationToLocationDto).collect(Collectors.toList());
    }


}
