// Load the AWS SDK for Node.js
import 'dotenv/config';
import SES from'aws-sdk/clients/ses';
// Set the region 
const cliente = new SES({
    region:`${process.env.AWS_REGION}`
  });
export default cliente


// Create sendEmail params 

