import { Category } from "./category";

export class Place {
    placeId: number;
    name: string;
    description: string;
    category: Category;
    image: string;
    lat: number;
    lng: number;
}
