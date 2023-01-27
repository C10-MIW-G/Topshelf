import { ProductDefinition } from "./productdefinition";

export interface Pantry {
    pantryId: number;
    name: string;
    stock: Set<ProductDefinition>;
}