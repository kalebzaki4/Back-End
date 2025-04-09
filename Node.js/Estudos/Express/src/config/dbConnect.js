import mongoose from 'mongoose';

async function conectaNaDatabase() {
    mongoose.connect("mongodb+srv://kaleb:kaleb@cluster0.kknmmsl.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")

    return mongoose.connection;
    
}

export default conectaNaDatabase; 
