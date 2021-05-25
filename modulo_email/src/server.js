import 'dotenv/config';
import express from 'express';
import UserController from './app/controllers/UserController';
import mensagem from './app/controllers/MensageController'
import BullBoard from 'bull-board';
import Queue from './app/lib/Queue';

const app = express();
BullBoard.setQueues(Queue.queues.map(queue => queue.bull));

app.use(express.json());
app.get('/',(req, res)=>{
        res.json("Servido de emais no ar").status(200)
})
app.post('/test', UserController.store);
app.post('/users/register', mensagem.store);

app.use('/admin/queues', BullBoard.UI);

app.listen(3333, () => {
  console.log('Server running on localhost:3333');
});