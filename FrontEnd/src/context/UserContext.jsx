import { createContext, useState } from "react";

export const UserContext = createContext();

const UserProvider = ({id}) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = userState(true);
}