import Queue from '../lib/Queue';
import clienteAWS from '../lib/MailAWS';
import cliente from '../lib/MailAWS';
import redisConfig from '../../config/redis';

export default {
  async store(req, res) {
    const { sendername,
      senderemail, 
      receivername,
      receiveremail,
      subject,
     text } = req.body;

   const user = {
     sendername,
      senderemail, 
      receivername,
      receiveremail,
      subject,
      text
   };
   console.log('user aws',redisConfig);

    // Adicionar job RegistrationMail na fila
    await Queue.add('RegistrationMailAWS', { user },
    {
      limiter: {
        max: 1,
        duration: 1000
      }
    }
    );
   

    await Queue.add('UserReport', { user });

    return res.json(user);
  }
};
