package se.lexicon.semester_app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.semester_app.dto.EmployeeDto;
import se.lexicon.semester_app.entity.Employee;
import se.lexicon.semester_app.exception.RecordNotFoundException;
import se.lexicon.semester_app.repo.EmployeeRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    EmployeeRepo employeeRepo;
    ModelMapper modelMapper;


    @Autowired
    public void setEmployeeRepo(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto findById(UUID id) throws RecordNotFoundException {
        if (id==null)throw new IllegalArgumentException("id should not be null");
        Optional<Employee> optionalEmployee=employeeRepo.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeDto convertedData = modelMapper.map(optionalEmployee.get(), EmployeeDto.class);
            return convertedData;
        }else {
            throw new RecordNotFoundException("EmployDto not found");
        }
    }

    @Override
    public EmployeeDto save(EmployeeDto dto) {
        if (dto==null)throw new IllegalArgumentException("EmployeeDto object should not be null");
        if (dto.getId() !=null)throw new IllegalArgumentException("Id should be null");
        Employee employeeEntity=modelMapper.map(dto,Employee.class);
        Employee savedEmployeeEntity=employeeRepo.save(employeeEntity);
        EmployeeDto convertEntityToDto=modelMapper.map(savedEmployeeEntity,EmployeeDto.class);
        return convertEntityToDto;
    }


    @Override
    public EmployeeDto update(EmployeeDto dto) throws RecordNotFoundException {
        if (dto==null)throw new IllegalArgumentException("EmployeeDto object should not be null");
        if (dto.getId()==null)throw new IllegalArgumentException("Id should not be null");
        Optional<Employee>optionalEmployee=employeeRepo.findById(dto.getId());
        if (optionalEmployee.isPresent()) {
            return modelMapper.map(employeeRepo.save(modelMapper.map(dto, Employee.class)), EmployeeDto.class);
        }else {
            throw new RecordNotFoundException("EmployeeDto not found");


        }


    }


    @Override
    public List<EmployeeDto> findAll() {
        List<Employee>employeeList=new ArrayList<>();
        employeeRepo.findAll().iterator().forEachRemaining(employeeList::add);
        List<EmployeeDto>employeeDtoList=employeeList.stream()
                .map(employee -> modelMapper.map(employee,EmployeeDto.class))
                .collect(Collectors.toList());
        return employeeDtoList;
    }
    }

