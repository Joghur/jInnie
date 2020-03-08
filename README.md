# jInnie

### Online invoice application
### backend in java for use with jInnie_frontend  
 
**Features**  

 * Keep a record of customers, wares and orders online.
 * Create invoices in pdf format

**Todo**  

 * Info has to be manually input to database at the moment  
 * Many other features not yet implemented (to many to mention at the moment)  
 * Automatic firm-info from environ-file (for pdf invoices)  
 * Better UI  
 * Refactoring  
 * Cleanup  

**What works**  
Orders, customers, itemTypes can be registered in backend (but not from frontend)  
Invoice pdf can be created from frontend  

**How to**  
Under construction  

# jInnie client

This is a client for login in or out of REST endpoint and for fetching data when logged in.  
Set REST endpoint URL in file **src/settings.js**  

## Deployment instructions
First clone project. [https://github.com/Joghur/jInnie_frontend](https://github.com/Joghur/jInnie_frontend)  
In cloned folder using a terminal enter:  
#### `npm install`  
and
#### `npm install react-router-dom`  
to install prerequisites

When all is ready to deploy:

#### `npm run build`
