package app.service.repairer;

import app.model.Reception;

public interface IRepairReception {

    Reception createReception(RepairReceptionRequest request);

    Reception readReception(long receptionID);
}
