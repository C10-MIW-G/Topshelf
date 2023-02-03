import { ProductDefinition } from "../product-definitions/productDefinition";

export interface StockProduct{
    stockProductId: number;
    name: string;
    expirationDate: Date;
    pantryId: number;

    productDefinition: ProductDefinition;
}