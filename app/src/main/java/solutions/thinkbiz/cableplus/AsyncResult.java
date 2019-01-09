package solutions.thinkbiz.cableplus;

/**
 * Created by User on 10-Sep-18.
 */

public interface AsyncResult<TData> {
    void success(TData data, String qty);
    void error(String error);
    void SendDataMethod(String name, String image, String qty, String Pid);
}
