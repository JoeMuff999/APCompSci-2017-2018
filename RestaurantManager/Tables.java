public  class Tables
{
    public boolean empty;
    public boolean dirty;
    public boolean openTab;

    public Tables()
    {
        

    }

    public Tables(boolean isEmpty, boolean isDirty)
    {
        empty = isEmpty;
        dirty = isDirty;
        openTab = false;
    }

    public void setEmpty(boolean emptyOrNot)
    {
        empty = emptyOrNot;
    }

    public void setDirty(boolean dirtyOrNot)
    {
        dirty = dirtyOrNot;
    }
    
    public void setOpenTab(boolean tabOrNot)
    {
        openTab = tabOrNot;
    }

    public String toString()
    {
        if(empty == true && dirty == false)
        {
            return "This Table is empty and not dirty";
        }
        else if(empty == false && dirty == false)
        {
            return "This table is full and not dirty";
        }
        else if(empty == true && dirty == true)
        {
            return "This table is empty and needs to be cleaned";
        }
        else
        {
            return " This table is full and dirty, but this also means you screwed up so yeah"; 
        }
    }

}