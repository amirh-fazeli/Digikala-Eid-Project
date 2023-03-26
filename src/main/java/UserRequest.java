public class UserRequest{
    public User sender;
    public int amount;
    public String status="not announced";

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public User getSender() {
        return sender;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return  "sender: " + sender.getUsername() +
                ", amount: " + amount +
                ", status: " + status;
    }
}
