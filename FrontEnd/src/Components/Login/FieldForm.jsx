const FieldForm = ({ label, type, name, handleChange, className }) => {
    return (
        <div className="field-form">
            <fieldset>
                <legend>{label}</legend>
                <input 
                    type={type} 
                    name={name} 
                    onChange={handleChange}
                    className={className}
                    required
                />
            </fieldset>
        </div>
    );
};

export default FieldForm;