import clienteAWS from '../lib/MailAWS';



export default {
  key: 'RegistrationMailAWS',
  
  async handle({ data }) {
    const { user } = data;
   
    var params = {
      Destination: { /* required */
       
        ToAddresses: [
          `${user.receivername} < ${user.receiveremail}>`,
          /* more items */
  
        ]
        
      },
      Message: { /* required */
        Body: { /* required */
          Html: {
           Charset: "UTF-8",
           Data: `${user.text}`
          },
          Text: {
           Charset: "UTF-8",
           Data: `${user.text}`
          }
         },
         Subject: {
          Charset: 'UTF-8',
          Data: `${user.subject}`
         }
        },
      Source: `${user.sendername}< ${user.senderemail}>`, /* required */
     
      
    };
    // Create the promise and SES service object

 clienteAWS.sendEmail(params).
 promise()

.then(
  function(data) {
    console.log(data.MessageId);
  }).catch(
    function(err) {
    console.error(err, err.stack);
  });
   
// Handle promise's fulfilled/rejected states

  }
  
};