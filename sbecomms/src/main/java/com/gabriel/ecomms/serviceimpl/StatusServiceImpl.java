package com.gabriel.ecomms.serviceimpl;
import com.gabriel.ecomms.entity.StatusData;
import com.gabriel.ecomms.model.Status;
import com.gabriel.ecomms.repository.StatusDataRepository;
import com.gabriel.ecomms.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class StatusServiceImpl implements StatusService {
	Logger logger = LoggerFactory.getLogger(StatusServiceImpl.class);
	@Autowired
	StatusDataRepository statusDataRepository;
	@Autowired
	@Override
	public Status[] getAll() {
		List<StatusData> statussData = new ArrayList<>();
		List<Status> statuss = new ArrayList<>();
		statusDataRepository.findAll().forEach(statussData::add);
		Iterator<StatusData> it = statussData.iterator();
		while(it.hasNext()) {
			StatusData statusData = it.next();
			Status status = new Status();
			status.setId(statusData.getId());
			status.setName(statusData.getName());
			statuss.add(status);
		}
		Status[] array = new Status[statuss.size()];
		for  (int i=0; i<statuss.size(); i++){
			array[i] = statuss.get(i);
		}
		return array;
	}
	@Override
	public Status create(Status status) {
		logger.info(" add:Input " + status.toString());
		StatusData statusData = new StatusData();
		statusData.setName(status.getName());
		statusData = statusDataRepository.save(statusData);
		logger.info(" add:Input " + statusData.toString());
			Status newStatus = new Status();
			newStatus.setId(statusData.getId());
			newStatus.setName(statusData.getName());
		return newStatus;
	}

	@Override
	public Status update(Status status) {
		Status updatedStatus = null;
		int id = status.getId();
		Optional<StatusData> optional  = statusDataRepository.findById(status.getId());
		if(optional.isPresent()){
			StatusData originalStatusData = new StatusData();
			originalStatusData.setId(status.getId());
			originalStatusData.setName(status.getName());
			originalStatusData.setCreated(optional.get().getCreated());
			StatusData statusData = statusDataRepository.save(originalStatusData);
			updatedStatus = new Status();
			updatedStatus.setId(statusData.getId());
			updatedStatus.setName(statusData.getName());
			updatedStatus.setCreated(statusData.getCreated());
			updatedStatus.setLastUpdated(statusData.getLastUpdated());
		}
		else {
			logger.error("Status record with id: " + Integer.toString(id) + " do not exist ");

		}
		return updatedStatus;
	}

	@Override
	public Status get(Integer id) {
		logger.info(" Input id >> "+  Integer.toString(id) );
		Status status = null;
		Optional<StatusData> optional = statusDataRepository.findById(id);
		if(optional.isPresent()) {
			logger.info(" Is present >> ");
			status = new Status();
			status.setId(optional.get().getId());
			status.setName(optional.get().getName());
			status.setCreated(optional.get().getCreated());
			status.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.info(" Failed >> unable to locate id: " +  Integer.toString(id)  );
		}
		return status;
	}
	@Override
	public void delete(Integer id) {
		Status status = null;
		logger.info(" Input >> " +  Integer.toString(id));
		Optional<StatusData> optional = statusDataRepository.findById(id);
		if( optional.isPresent()) {
			StatusData statusDatum = optional.get();
			statusDataRepository.delete(optional.get());
			logger.info(" Successfully deleted Status record with id: " + Integer.toString(id));
			status = new Status();
			status.setId(optional.get().getId());
			status.setName(optional.get().getName());
			status.setCreated(optional.get().getCreated());
			status.setLastUpdated(optional.get().getLastUpdated());
		}
		else {
			logger.error(" Unable to locate status with id:" +  Integer.toString(id));
		}
	}
}
