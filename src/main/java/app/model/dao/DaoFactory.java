package app.model.dao;

import app.model.dao.impl.DaoFactoryImpl;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract IDishDao createDishDao();
    public abstract IUserDao createUserDao();
    public abstract ICategoryDao createCategoryDao();
    public abstract IOrderDao createOrderDao();
    public abstract IOrderHasDishDao createOrderHasDishDao();
    public abstract ICheckDao createCheckDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new DaoFactoryImpl();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
