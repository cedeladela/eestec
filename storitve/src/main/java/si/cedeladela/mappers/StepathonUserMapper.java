package si.cedeladela.mappers;

import si.cedeladela.dtos.LocationDto;
import si.cedeladela.entitete.Location;
import si.cedeladela.entitete.StepathonUser;
import si.cedeladela.dtos.StepathonUserDto;

import java.util.List;
import java.util.stream.Collectors;

public class StepathonUserMapper {

    public static StepathonUserDto stepathonUserToStepathonUserDto(StepathonUser stepathonUser) {
        StepathonUserDto stepathonUserDto = new StepathonUserDto();
        stepathonUserDto.setId(stepathonUser.getId());
        stepathonUserDto.setUsername(stepathonUser.getUsername());
        stepathonUserDto.setPassword(stepathonUser.getPassword());
        stepathonUserDto.setScore(stepathonUser.getScore());
        stepathonUserDto.setSteps(stepathonUser.getSteps());
        List<LocationDto> locationDtos = LocationMapper.locationToLocationDto(stepathonUser.getLocations());
        stepathonUserDto.setLocations(locationDtos);
        return stepathonUserDto;
    }

    public static List<StepathonUserDto> stepathonUserToStepathonUserDto(List<StepathonUser> stepathonUsers) {
        return stepathonUsers.stream().map(StepathonUserMapper::stepathonUserToStepathonUserDto).collect(Collectors.toList());
    }

    public static StepathonUser stepathonUserDtoToStepathonUser(StepathonUserDto stepathonUserDto) {
        StepathonUser stepathonUser = new StepathonUser();
        stepathonUser.setId(stepathonUserDto.getId());
        stepathonUser.setUsername(stepathonUserDto.getUsername());
        stepathonUser.setPassword(stepathonUserDto.getPassword());
        stepathonUser.setScore(stepathonUserDto.getScore());
        stepathonUser.setSteps(stepathonUserDto.getSteps());
        List<Location> locations = LocationMapper.locationDtoToLocation(stepathonUserDto.getLocations());
        stepathonUser.setLocations(locations);
        return stepathonUser;
    }


}
