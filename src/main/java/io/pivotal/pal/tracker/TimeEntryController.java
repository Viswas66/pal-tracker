package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TimeEntryController {

    @Autowired
    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository=timeEntryRepository;
    }
    @PostMapping(value = "/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntryToCreate);

        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping(value = "/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry=timeEntryRepository.find(timeEntryId);
        if(timeEntry==null)
        {
             return new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(timeEntry, HttpStatus.OK);
    }

    @GetMapping(value = "/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list=timeEntryRepository.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping(value = "/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry timeEntryUpdated=timeEntryRepository.update(timeEntryId,timeEntry);
        if(timeEntryUpdated==null)
        {
            return new ResponseEntity(timeEntryUpdated, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(timeEntryUpdated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
