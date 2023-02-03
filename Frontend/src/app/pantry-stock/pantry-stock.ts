import { ProductDefinition } from "../product-definitions/productDefinition";

export interface PantryStock{
    stock: Set<ProductDefinition>;
}