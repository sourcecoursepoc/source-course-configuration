package com.ust.sourcecourse.configuration.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.ust.sourcecourse.configuration.entity.SourcePocEntity;
import com.ust.sourcecourse.configuration.repository.SourcePocRepository;

@RestController

public class SourcePocController {
private SourcePocRepository	 sourcepocrepository;
@GetMapping("/SourcePocEntity")
public List<SourcePocEntity> retrieveAllSourcepoccourse() {
	return SourcePocRepository.findAll();
}
//@GetMapping("/SourcePocEntity/{id}")
//public SourcePocEntity retriveSourcePocEntity(@PathVariable long id) throws SourcePocEntityNotFoundException {
//	Optional<SourcePocEntity> SourcePocEntity = sourcepocrepository.findById(id);
// if(SourcePocEntity.isEmpty())	
//	 throw new SourcePocEntityNotFoundException("id-"+id);
// return SourcePocEntity.get();
//	 
// 
//}
//@DeleteMapping("/SourcePocEntity/{id}")
//public void deleteSourcePocEntity(@PathVariable long id) {
//	SourcePocRepository.deleteById(id);
//	
//}
@PostMapping("/SourcePocEntity")
public UriComponents createSourcePocEntity(@RequestBody SourcePocEntity sourcepocentity) {
SourcePocEntity savedSourcePocEntity = 	SourcePocRepository.save(sourcepocentity);
URI location = ServletUriComponentsBuilder.fromCurrentRequest()
.path("/{id}")
.buildAndExpand(savedSourcePocEntity.getDbid())
.toUri();
	
return SourcePocEntity.created(location)
		.build();

}
}
//@PutMapping("/SourcePocEntity/{id}")
//public ResponseEntity<Object> SourcePocEntity(@RequestBody SourcePocEntity sourcePocEntity, @PathVariable long id) {
//
//    Optional<SourcePocEntity> sourcePocEntityOptional = SourcePocRepository.findById(id);
//
//    if (sourcePocEntityOptional.isEmpty())
//        return ResponseEntity.notFound().build();
//
//    sourcePocEntity.setDbid(id);
//
//    SourcePocRepository.save(sourcePocEntity);
//
//    return ResponseEntity.noContent()
//            .build();
//}
//
//}