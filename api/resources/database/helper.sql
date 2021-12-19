CREATE TABLE "public"."tweets" (
    "id" uuid NOT NULL,
    "body" text NOT NULL,
    "created_at" timestamp,
    "username" varchar NOT NULL,
    PRIMARY KEY ("id")
);