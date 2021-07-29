import React from 'react';
import FormResponsavel from './components/FormResponsavel'
import FormAtividadeDiaria from './components/FormAtividadeDiaria';


function App() {
  return (
    <div className="App">
      <h1>Responsavel Form</h1>
      <FormResponsavel></FormResponsavel>
      <h2>Atividade Form</h2>
      <FormAtividadeDiaria></FormAtividadeDiaria>
    </div>
  );
}

export default App;
