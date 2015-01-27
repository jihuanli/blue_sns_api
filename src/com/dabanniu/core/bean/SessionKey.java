package com.dabanniu.core.bean;


public class SessionKey {
    //cookie user info
    private Long uID;
    
    private Long creationDate;
    
    private Integer expirationDate;

	public Long getuID() {
		return uID;
	}

	public Long getCreationDate() {
		return creationDate;
	}

	public Integer getExpirationDate() {
		return expirationDate;
	}

	public void setuID(Long uID) {
		this.uID = uID;
	}

	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}

	public void setExpirationDate(Integer expirationDate) {
		this.expirationDate = expirationDate;
	}
	

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SessionKey [uID=" + uID + ", creationDate=" + creationDate + ", expirationDate=" + expirationDate + "]";
    }
	
    public String toSessionKey() {
        return  uID + "@" + (int)(creationDate/1000) + "@" + expirationDate;
    }
    
    public byte[] getBytes(){
    	return toSessionKey().getBytes();
    }
    
    public boolean isExpired(){
    	if((creationDate+30*24*60*60*1000l)<System.currentTimeMillis()){
    		return true;
    	}
    	return false;
    }
}
