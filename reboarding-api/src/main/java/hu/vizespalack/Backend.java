package hu.vizespalack;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface Backend {

    Integer getWorkerPosition(@NotNull Worker worker);

    Integer getWorkerPosition(@NotNull EntryDate date, @NotNull Worker worker);

    List<WaitingListEntry> getWorkerPositions(@NotNull Worker worker, Integer capacity);

    List<WaitingListEntry> getWaitingList(@NotNull EntryDate date);

    void clearWaitingList();

    List<Worker> getWorkersInOffice(@NotNull EntryDate date);

    Integer registerWorkerToDay(@NotNull Worker worker, @NotNull EntryDate date);

    Integer getNumberOfWorkersInOffice();

    Boolean addWorkerToOffice(@NotNull Worker worker, Integer capacity);

    Boolean removeWorkerFromOffice(@NotNull Worker worker);
}
