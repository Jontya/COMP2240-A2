public class Customer implements Runnable{
    private int arriveTime;
    private int eatingTime;
    private int seatedTime;
    private Parlour parlour;
    private String customerID;

    public Customer(int _arriveTime, String _customerID, int _customerTime, Parlour _parlour){
        arriveTime = _arriveTime;
        customerID = _customerID;
        eatingTime = _customerTime;
        parlour = _parlour;
    }

    public void setSeatedTime(int _seatedTime){
        seatedTime = _seatedTime;
    }

    public int getArriveTime(){
        return arriveTime;
    }

    public int getEatingTime(){
        return eatingTime;
    }

    @Override
    public void run() {
        try{
            parlour.getSemaphore().acquire();
        }
        catch (Exception e){
            return;
        }
        finally{
            System.out.println(customerID);
            /*
            boolean checked = false;
            while(parlour.getCurrTime() != (seatedTime + eatingTime) && !parlour.allChecked()){
                while(!checked){
                    try{
                        parlour.getTimeSemaphore().acquire();
                    }
                    catch (Exception e){
                        return;
                    }
                    finally{
                        checked = true;
                        parlour.incCheckCount();
                        parlour.getTimeSemaphore().release();
                    }
                }
            }

            System.out.println(customerID +  " " + parlour.getCurrTime());
            while(parlour.allChecked()){
                try{
                    parlour.getTimeSemaphore().acquire();
                }
                catch (Exception e){
                    return;
                }
                finally{
                    parlour.nextEvent();
                    parlour.resetCheckCount();
                    parlour.getTimeSemaphore().release();
                }
            }

            parlour.getSemaphore().release();*/
        }
    }
}
