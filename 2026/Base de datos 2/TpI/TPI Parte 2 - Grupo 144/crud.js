import { MongoClient } from 'mongodb';

const CONNECTION_STRING =
  'mongodb://ximenasosa:MongoTP72026@ac-ngulq80-shard-00-00.uxlzy5e.mongodb.net:27017,ac-ngulq80-shard-00-01.uxlzy5e.mongodb.net:27017,ac-ngulq80-shard-00-02.uxlzy5e.mongodb.net:27017/?ssl=true&replicaSet=atlas-11wj2q-shard-0&authSource=admin&appName=Cluster0';
const client = new MongoClient(CONNECTION_STRING);

async function main() {
  await client.connect();
  console.log('Conexión exitosa a MongoDB Atlas\n');

  const db = client.db('tpi_taller_carpinteria');
  const clientes = db.collection('clientes');

  // CREATE
  console.log('--- CREATE: Insertar nuevo cliente ---');
  const nuevoCliente = {
    nombre: 'Roberto Sánchez',
    email: 'roberto.sanchez@email.com',
    contacto: {
      telefono: '11-5555-1234',
      direccion: 'Calle Lavalle 300, Mendoza',
    },
    activo: true,
  };
  const insertResult = await clientes.insertOne(nuevoCliente);
  const nuevoId = insertResult.insertedId;
  console.log('Documento insertado con _id:', nuevoId, '\n');

  // READ
  console.log('--- READ: Listar clientes activos ---');
  const clientesActivos = await clientes.find({ activo: true }).toArray();
  console.log(`Se encontraron ${clientesActivos.length} cliente(s) activo(s):`);
  clientesActivos.forEach((c) => {
    console.log(` - ${c.nombre} | ${c.email}`);
  });
  console.log('');

  // UPDATE
  console.log('--- UPDATE: Actualizar email del cliente ---');
  const updateResult = await clientes.updateOne(
    { _id: nuevoId },
    { $set: { email: 'roberto.sanchez.nuevo@email.com' } }
  );
  console.log(`Documentos modificados: ${updateResult.modifiedCount}\n`);

  // DELETE LÓGICO
  console.log('--- DELETE LÓGICO: Desactivar cliente ---');
  const deleteResult = await clientes.updateOne(
    { _id: nuevoId },
    { $set: { activo: false } }
  );
  console.log(`Documentos desactivados: ${deleteResult.modifiedCount}`);

  const verificacion = await clientes.findOne({ _id: nuevoId });
  console.log(` - ${verificacion.nombre} | activo: ${verificacion.activo}\n`);

  console.log('Ciclo CRUD completado correctamente.');
}

try {
  await main();
} catch (error) {
  console.error('Error:', error);
} finally {
  await client.close();
  console.log('Conexión cerrada.');
}