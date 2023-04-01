package si.cedeladela.mappers;

import si.cedeladela.entitete.StepathonUser;
import si.cedeladela.dtos.StepathonUserDto;

import java.util.List;
import java.util.stream.Collectors;

public class StepathonUserMapper {

    public static StepathonUserDto stepathonUserToStepathonUserDto(StepathonUser stepathonUser) {
        StepathonUserDto stepathonUserDto = new StepathonUserDto();
        stepathonUserDto.setUsername(stepathonUser.getUsername());
        stepathonUserDto.setPassword(stepathonUser.getPassword());
        stepathonUserDto.setScore(stepathonUser.getScore());
        stepathonUserDto.setSteps(stepathonUser.getSteps());
        return stepathonUserDto;
    }

    public static List<StepathonUserDto> stepathonUserToStepathonUserDto(List<StepathonUser> stepathonUsers) {
        return stepathonUsers.stream().map(StepathonUserMapper::stepathonUserToStepathonUserDto).collect(Collectors.toList());
    }


}
